using AutoRest.Core;

namespace AutoRest.Java
{
    /// <summary>
    /// Settings that are used by the Java AutoRest Generator.
    /// </summary>
    public class JavaSettings
    {
        public Settings AutoRestSettings { private get; set; }

        public bool IsAzure { get; set; }

        public bool IsFluent { get; set; }

        public bool IsAzureOrFluent => IsAzure || IsFluent;

        public bool AddCredentials
        {
            set => AutoRestSettings.AddCredentials = value;
        }

        public bool RegenerateManagers { get; set; }

        public bool RegeneratePom { get; set; }

        public string FileHeaderText { get; set; }

        public int MaximumJavadocCommentWidth { get; set; }

        public string ServiceName { get; set; }

        public string Package { get; set; }
    }
}
