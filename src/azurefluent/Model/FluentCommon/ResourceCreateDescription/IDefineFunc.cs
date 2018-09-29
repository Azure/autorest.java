// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.


namespace AutoRest.Java.Azure.Fluent.Model
{
    /// <summary>
    /// Contract representing "define" method in it's normal or generalized form.
    /// 
    /// "define" method starts fluent defintion of a resource.
    /// </summary>
    public interface IDefineFunc
    {
        /// <summary>
        /// True if this instance belongs to a resource that is creatable.
        /// </summary>
        bool IsDefineSupported { get; }
        /// <summary>
        /// Gets the normal name of the "define" method (it's a string const "define")
        /// </summary>
        string MethodName { get; }
        /// <summary>
        /// Gets the declaration of "define" method in it's normal form.
        /// 
        /// e.g. VirtualMachine.Defintion.Blank define(string name).
        /// </summary>
        string MethodDecl { get; }
        /// <summary>
        /// Gets the implementation of "define" method in it's normal form.
        /// </summary>
        string MethodImpl { get; }
        /// <summary>
        /// Gets the generalized name of the "define" method.
        /// </summary>
        string GeneralizedMethodName { get; }
        /// <summary>
        /// Gets the declaration of "define" method in it's generalized form.
        /// 
        /// e.g. EventHubAuthorizationRule.Defintion.Blank defineEventHubAuthorizationRule(string name).
        /// </summary>
        string GeneralizedMethodDecl { get; }
        /// <summary>
        /// Gets the implementation of "define" method in it's generalized form.
        /// </summary>
        string GeneralizedMethodImpl { get; }
    }
}
