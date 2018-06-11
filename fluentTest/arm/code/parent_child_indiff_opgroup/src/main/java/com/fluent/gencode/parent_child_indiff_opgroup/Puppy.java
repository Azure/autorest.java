/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 */

package com.fluent.gencode.parent_child_indiff_opgroup;

import com.microsoft.azure.arm.model.HasInner;
import com.fluent.gencode.parent_child_indiff_opgroup.implementation.PuppyInner;
import com.microsoft.azure.arm.model.Indexable;
import com.microsoft.azure.arm.model.Refreshable;
import com.microsoft.azure.arm.model.Updatable;
import com.microsoft.azure.arm.model.Appliable;
import com.microsoft.azure.arm.model.Creatable;
import com.microsoft.azure.arm.resources.models.HasManager;
import com.fluent.gencode.parent_child_indiff_opgroup.implementation.Parent_Child_Indiff_OpGroupManager;
import java.util.Map;
import org.joda.time.DateTime;

/**
 * Type representing Puppy.
 */
public interface Puppy extends HasInner<PuppyInner>, Indexable, Refreshable<Puppy>, Updatable<Puppy.Update>, HasManager<Parent_Child_Indiff_OpGroupManager> {
    /**
     * @return the animalSizeGB value.
     */
    Integer animalSizeGB();

    /**
     * @return the creationData value.
     */
    CreationData creationData();

    /**
     * @return the id value.
     */
    String id();

    /**
     * @return the location value.
     */
    String location();

    /**
     * @return the managedBy value.
     */
    String managedBy();

    /**
     * @return the name value.
     */
    String name();

    /**
     * @return the osType value.
     */
    ColorTypes osType();

    /**
     * @return the provisioningState value.
     */
    String provisioningState();

    /**
     * @return the sku value.
     */
    PuppySku sku();

    /**
     * @return the tags value.
     */
    Map<String, String> tags();

    /**
     * @return the timeCreated value.
     */
    DateTime timeCreated();

    /**
     * @return the type value.
     */
    String type();

    /**
     * The entirety of the Puppy definition.
     */
    interface Definition extends DefinitionStages.Blank, DefinitionStages.WithDog, DefinitionStages.WithCreationData, DefinitionStages.WithLocation, DefinitionStages.WithCreate {
    }

    /**
     * Grouping of Puppy definition stages.
     */
    interface DefinitionStages {
        /**
         * The first stage of a Puppy definition.
         */
        interface Blank extends WithDog {
        }

        /**
         * The stage of the puppy definition allowing to specify Dog.
         */
        interface WithDog {
           /**
            * Specifies resourceGroupName, dogName.
            */
            WithCreationData withExistingDog(String resourceGroupName, String dogName);
        }

        /**
         * The stage of the puppy definition allowing to specify CreationData.
         */
        interface WithCreationData {
           /**
            * Specifies creationData.
            */
            WithLocation withCreationData(CreationData creationData);
        }

        /**
         * The stage of the puppy definition allowing to specify Location.
         */
        interface WithLocation {
           /**
            * Specifies location.
            */
            WithCreate withLocation(String location);
        }

        /**
         * The stage of the puppy definition allowing to specify AnimalSizeGB.
         */
        interface WithAnimalSizeGB {
            /**
             * Specifies animalSizeGB.
             */
            WithCreate withAnimalSizeGB(Integer animalSizeGB);
        }

        /**
         * The stage of the puppy definition allowing to specify OsType.
         */
        interface WithOsType {
            /**
             * Specifies osType.
             */
            WithCreate withOsType(ColorTypes osType);
        }

        /**
         * The stage of the puppy definition allowing to specify Sku.
         */
        interface WithSku {
            /**
             * Specifies sku.
             */
            WithCreate withSku(PuppySku sku);
        }

        /**
         * The stage of the puppy definition allowing to specify Tags.
         */
        interface WithTags {
            /**
             * Specifies tags.
             */
            WithCreate withTags(Map<String, String> tags);
        }

        /**
         * The stage of the definition which contains all the minimum required inputs for
         * the resource to be created (via {@link WithCreate#create()}), but also allows
         * for any other optional settings to be specified.
         */
        interface WithCreate extends Creatable<Puppy>, DefinitionStages.WithAnimalSizeGB, DefinitionStages.WithOsType, DefinitionStages.WithSku, DefinitionStages.WithTags {
        }
    }
    /**
     * The template for a Puppy update operation, containing all the settings that can be modified.
     */
    interface Update extends Appliable<Puppy>, UpdateStages.WithAnimalSizeGB, UpdateStages.WithOsType, UpdateStages.WithSku, UpdateStages.WithTags {
    }

    /**
     * Grouping of Puppy update stages.
     */
    interface UpdateStages {
        /**
         * The stage of the puppy update allowing to specify AnimalSizeGB.
         */
        interface WithAnimalSizeGB {
            /**
             * Specifies animalSizeGB.
             */
            Update withAnimalSizeGB(Integer animalSizeGB);
        }

        /**
         * The stage of the puppy update allowing to specify OsType.
         */
        interface WithOsType {
            /**
             * Specifies osType.
             */
            Update withOsType(ColorTypes osType);
        }

        /**
         * The stage of the puppy update allowing to specify Sku.
         */
        interface WithSku {
            /**
             * Specifies sku.
             */
            Update withSku(PuppySku sku);
        }

        /**
         * The stage of the puppy update allowing to specify Tags.
         */
        interface WithTags {
            /**
             * Specifies tags.
             */
            Update withTags(Map<String, String> tags);
        }

    }
}
