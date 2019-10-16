using AutoRest.Core;
using AutoRest.Core.Model;
using AutoRest.Core.Parsing;
using AutoRest.Core.Utilities;
using Microsoft.Perks.JsonRPC;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using IAnyPlugin = AutoRest.Core.Extensibility.IPlugin<AutoRest.Core.Extensibility.IGeneratorSettings, AutoRest.Core.IModelSerializer<AutoRest.Core.Model.CodeModel>, AutoRest.Core.ITransformer<AutoRest.Core.Model.CodeModel>, AutoRest.Core.CodeGenerator, AutoRest.Core.CodeNamer, AutoRest.Core.Model.CodeModel>;

namespace AutoRest.Java
{
    public class Program : NewPlugin
    {
        public static int Main(string[] args)
        {
            if(args != null && args.Length > 0 && args[0] == "--server")
            {
                var connection = new Connection(Console.OpenStandardOutput(), Console.OpenStandardInput());
                connection.Dispatch("GetPluginNames", () => Task.FromResult<IEnumerable<string>>(new[] { "java" }));
                connection.Dispatch<string, string, bool>("Process", (plugin, sessionId) => new Program(connection, plugin, sessionId).Process());
                connection.DispatchNotification("Shutdown", connection.Stop);

                // wait for something to do.
                connection.GetAwaiter().GetResult();

                Console.Error.WriteLine("Shutting Down");
                return 0;
            }
            Console.WriteLine("This is not an entry point.");
            Console.WriteLine("Please invoke this extension through AutoRest.");
            return 1;
        }

        public Program(Connection connection, string plugin, string sessionId) : base(connection, plugin, sessionId) { }

        private T GetXmsCodeGenSetting<T>(CodeModel codeModel, string name)
        {
            try
            {
                return (T)Convert.ChangeType(
                    codeModel.CodeGenExtensions[name], 
                    typeof(T).GenericTypeArguments.Length == 0 ? typeof(T) : typeof(T).GenericTypeArguments[0] // un-nullable
                );
            }
            catch
            {
                return default(T);
            }
        }

        protected override async Task<bool> ProcessInternal()
        {
            string[] files = await ListInputs().ConfigureAwait(false);
            if (files.Length != 1)
            {
                throw new Exception($"Generator received incorrect number of inputs: {files.Length} : {string.Join(",", files)}");
            }

            string modelAsJson = (await ReadFile(files[0])).EnsureYamlIsJson();
            CodeModel codeModelT = new ModelSerializer<CodeModel>().Load(modelAsJson);

            // build settings
            string altNamespace = (await GetValue<string[]>("input-file") ?? new[] { "" }).FirstOrDefault()?.Split('/').Last().Split('\\').Last().Split('.').First();
            
            new Settings
            {
                Namespace = await GetValue("namespace"),
                ClientName = GetXmsCodeGenSetting<string>(codeModelT, "name") ?? await GetValue("override-client-name"),
                PayloadFlatteningThreshold = GetXmsCodeGenSetting<int?>(codeModelT, "ft") ?? await GetValue<int?>("payload-flattening-threshold") ?? 0,
                AddCredentials = await GetValue<bool?>("add-credentials") ?? false,
                Host = this
            };
            string header = await GetValue("license-header");
            if (header != null)
            {
                Settings.Instance.Header = header;
            }

            Settings.Instance.CustomSettings["InternalConstructors"] = GetXmsCodeGenSetting<bool?>(codeModelT, "internalConstructors") ?? await GetValue<bool?>("use-internal-constructors") ?? false;
            Settings.Instance.CustomSettings["SyncMethods"] = GetXmsCodeGenSetting<string>(codeModelT, "syncMethods") ?? await GetValue("sync-methods") ?? "essential";
            Settings.Instance.CustomSettings["UseDateTimeOffset"] = GetXmsCodeGenSetting<bool?>(codeModelT, "useDateTimeOffset") ?? await GetValue<bool?>("use-datetimeoffset") ?? false;
            Settings.Instance.CustomSettings["ClientSideValidation"] = await GetValue<bool?>("client-side-validation") ?? false;

            string[] expectedSettingNames = new[]
            {
                "azure-arm",
                "fluent",
                "non-null-annotations",
                "string-dates",
                "client-type-prefix",
                "generate-client-interfaces",
            };
            foreach (string expectedSettingName in expectedSettingNames)
            {
                Settings.Instance.CustomSettings[expectedSettingName] = await GetValue(expectedSettingName).ConfigureAwait(false);
            }

            Settings.Instance.MaximumCommentColumns = await GetValue<int?>("max-comment-columns") ?? Settings.DefaultMaximumCommentColumns;
            Settings.Instance.OutputFileName = await GetValue<string>("output-file");
            Settings.Instance.CustomSettings["ModelProperties"] = await GetValue<Dictionary<string, Dictionary<string, bool>>>("model-properties")
                ?? new Dictionary<string, Dictionary<string, bool>>();
            
            // process
            IAnyPlugin plugin = new JavaPlugin();

            Settings.PopulateSettings(plugin.Settings, Settings.Instance.CustomSettings);
            
            using (plugin.Activate())
            {
                Settings.Instance.Namespace = Settings.Instance.Namespace ?? CodeNamer.Instance.GetNamespaceName(altNamespace);
                CodeModel codeModel = plugin.Serializer.Load(modelAsJson);
                codeModel = plugin.Transformer.TransformCodeModel(codeModel);
                if (await GetValue<bool?>("sample-generation") ?? false)
                {
                    await plugin.CodeGenerator.GenerateSamples(codeModel).ConfigureAwait(false);
                }
                else
                {
                    await plugin.CodeGenerator.Generate(codeModel).ConfigureAwait(false);
                }
            }

            // write out files
            MemoryFileSystem outFS = Settings.Instance.FileSystemOutput;
            string[] outFiles = outFS.GetFiles("", "*", System.IO.SearchOption.AllDirectories);
            foreach (string outFile in outFiles)
            {
                WriteFile(outFile, outFS.ReadAllText(outFile), null);
            }

            return true;
        }
    }
}