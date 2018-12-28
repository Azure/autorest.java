// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using System;
using System.Collections.Generic;
using System.Globalization;
using System.Linq;
using System.Net;
using System.Text;
using AutoRest.Core;
using AutoRest.Core.Utilities;
using AutoRest.Extensions;
using AutoRest.Core.Model;
using Newtonsoft.Json;
using AutoRest.Core.Utilities.Collections;
using Newtonsoft.Json.Linq;
using AutoRest.Java.Model;
using System.Text.RegularExpressions;

namespace AutoRest.Java
{
    public class PackageInfoWriter : IWriter<PackageInfo, JavaFile>
    {
        private JavaSettings settings;
        private WriterFactory factory;

        public PackageInfoWriter(WriterFactory factory)
        {
            this.factory = factory;
            this.settings = factory.Settings;
        }

        public void Write(PackageInfo packageInfo, JavaFile javaFile)
        {            
            if (!string.IsNullOrEmpty(settings.FileHeaderText))
            {
                javaFile.LineComment(settings.MaximumJavadocCommentWidth, (comment) =>
                {
                    comment.Line(settings.FileHeaderText);
                });
                javaFile.Line();
            }

            javaFile.JavadocComment(settings.MaximumJavadocCommentWidth, (comment) =>
            {
                foreach (var desc in packageInfo.Description.Split(new [] { '\r', '\n'}))
                {
                    comment.Description(desc);
                }
            });

            javaFile.Package(packageInfo.Package);
        }
    }
}