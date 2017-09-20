using System;
using System.Collections.Generic;
using AutoRest.Core.Model;
using AutoRest.Core.Utilities;
using AutoRest.Java.Model;

namespace AutoRest.Java.Azure.Model
{
    /// <summary>
    /// Contains properties common between CodeModel and MethodGroup for template rendering.
    /// </summary>
    public interface IMethodGroupJva
    {
        IEnumerable<MethodJva> Methods { get; }
        string LoggingContext { get; }
        string ServiceType { get; }
        string Name { get; }
        CodeModel CodeModel { get; }
    }
}
