// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

namespace AutoRest.Java.Azure.Fluent.Model
{
    /// <summary>
    /// Type that represents 'wrapModel(string name)' function in it's normal or generalized form.
    /// 'wrapModel(string name)' method wraps new standard inner model in it's impl model 
    /// 
    /// Such wrapping is done only when a new Azure resource representing inner model needs to be created.
    /// 'wrapModel' method appears in fluent method group (fluent collection) impl.
    /// </summary>
    public interface IWrapNewModelFunc
    {
        /// <summary>
        /// True if this instance belongs to a resource that is creatable hence wrapModel is needed.
        /// </summary>
        bool IsWrapNewModelSupported { get; }
        /// <summary>
        /// Gets the normal name of the "wrapModel" method (it's a string const "wrapModel")
        /// </summary>
        string MethodName { get; }
        /// <summary>
        /// Gets the implementation of "wrapModel" method in it's normal form.
        /// </summary>
        /// <param name="applyOverride">when true, an implementation of the abstract method'ImplT CreatableWrappersImpl::wrapModel(string name)'
        /// will be returned, otherwise provide a private method implementation of wrapModel if wrapping can be done.
        /// </param>
        /// <returns>the method implemenetation</returns>
        string MethodImpl(bool applyOverride);
        /// <summary>
        /// Gets the generalized name of the "wrapModel" method.
        /// </summary>
        string GeneralizedMethodName { get; }
        /// <summary>
        /// Gets the implementation of "wrapModel" method in it's generalized form.
        /// </summary>
        string GeneralizedMethodImpl { get; }
    }
}
