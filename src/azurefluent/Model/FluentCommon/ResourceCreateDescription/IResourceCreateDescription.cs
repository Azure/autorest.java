// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using System.Collections.Generic;

namespace AutoRest.Java.Azure.Fluent.Model
{
    /// <summary>
    /// Describes creation of a 'Standard Model' (which describes an Azure resource) of a fluent method group.
    /// </summary>
    public interface IResourceCreateDescription
    {
        /// <summary>
        /// Gets the method representing apiCall to create the resource. Null will be returned if resource cannot
        /// be created. 
        /// The "SupportsCreating" or "CreateType" property can be used to check whether the resource can be created.
        /// </summary>
        StandardFluentMethod CreateMethod { get; }
        /// <summary>
        /// The creation type describing how the resource exists in Azure. CreateType.None will be returned if resource
        /// cannot be created.
        /// </summary>
        CreateType CreateType { get; }
        /// <summary>
        /// Describes the "define" method that begins the create definition of the resource. Description will be still
        ///  cannot be created, in this case DefineFunc.IsDefineSupported will be false.
        /// </summary>
        IDefineFunc DefineFunc { get; }
        /// <summary>
        /// The imports needed for an implementation of method group inorder to support creation of it's 'Standard Model'.
        /// </summary>
        HashSet<string> ImportsForMethodGroupImpl { get; }
        /// <summary>
        /// The imports needed for an method group interface inorder to support creation of it's 'Standard Model'.
        /// </summary>
        HashSet<string> ImportsForMethodGroupInterface { get; }
        /// <summary>
        /// The imports needed for the 'Standard Model' interface whose creation this description describes.
        /// </summary>
        HashSet<string> ImportsForModelInterface { get; }
        /// <summary>
        /// The types that method group (which supports creation of it's standard model) extends from.
        /// </summary>
        HashSet<string> MethodGroupInterfaceExtendsFrom { get; }
        /// <summary>
        /// True if the standard model of the fluent method group can be created, false if there is no 'Standard Model' or
        /// model is not creatable.
        /// </summary>
        bool SupportsCreating { get; }
        /// <summary>
        /// Describes "ModelInner wrap(string name)" method, which creates an instance of inner model that capture the properties
        /// user sets and used as payload to create the resource.
        /// 
        /// Description will be still if the resource cannot be created, in this case  WrapNewModelFunc.IsWrapNewModelSupported
        /// will be false.
        /// </summary>
        IWrapNewModelFunc WrapNewModelFunc { get; }
    }
}