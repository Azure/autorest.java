package com.azure.autorest.extension.base.model.codemodel;

/**
 */
public enum TestScenarioStepType {

    /**
     * Step to run a swagger operation defined rest call. This may not be just one http call.
     *
     * If the operation is a long running operation (LRO), then follow the LRO polling strategy.
     * Response statusCode must be 200 if the LRO succeeded, no matter what code the initial response is.
     * If the LRO is PUT/PATCH, the runner should automatically insert a GET after the polling to verify the resource update result.
     * If the operation is DELETE, then after the operation, the runner should automatically insert a GET to verify resource cannot be found.
     * Rest call step could be defined either by an example file, or by resourceName tracking and update.
     */
    restCall,
    /**
     * Step to deploy ARM template to the scope.
     */
    stepArmTemplate,
    /**
     * Step to deploy ARM deployment script to the scope.
     */
    stepArmDeploymentScript


}
