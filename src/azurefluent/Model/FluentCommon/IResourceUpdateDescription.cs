using System.Collections.Generic;

namespace AutoRest.Java.Azure.Fluent.Model
{
    public interface IResourceUpdateDescription
    {
        HashSet<string> ImportsForModelInterface { get; }
        bool SupportsUpdating { get; }
        FluentMethod UpdateMethod { get; }
        UpdateType UpdateType { get; }
    }
}