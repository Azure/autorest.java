using AutoRest.Core.Model;
using System.Collections.Generic;

namespace AutoRest.Java.Azure.Model
{
    /// <summary>
    /// Contains properties common between CodeModel and MethodGroup for template rendering.
    /// </summary>
    public interface IMethodGroupJva
    {
        IEnumerable<Method> Methods { get; }
        string LoggingContext { get; }
        string ServiceType { get; }
        string Name { get; }
        CodeModel CodeModel { get; }
    }
}
