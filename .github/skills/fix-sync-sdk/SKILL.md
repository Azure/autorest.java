---
name: fix-sync-sdk
description: '**WORKFLOW SKILL** - Update TypeSpec project, re-generate SDK, to fix error on SDK project. USE FOR: "fix sdk lib".
---

# Skill Instructions

## Request

The request would come as a form of:
- "Fix sdk lib <project-name>“

## Required repositories

- Azure/azure-sdk-for-java, cloned at "../azure-sdk-for-java"
  Call this folder "sdk repo" for short.
- Azure/azure-rest-api-specs, cloned at "../azure-rest-api-specs"
  Call this folder "specs repo" for short.

You have full access to these folders of local clone.

## Steps

### Before you start

Verify that specs repo is on "main" branch, and is up-to-date.
Note the commit hash of "main" branch, call it "<specs-main-commit>".

There can be multiple projects to fix in one request. Repeat the following steps for each "<project-name>".

### Find the project in sdk repo

Search for folder of "<project-name>" under "/sdk" in sdk repo. It should be in the form of "/sdk/<service-name>/<project-name>".

"<project-path>" refers to the full path of the project folder.

### Find the TypeSpec project in specs repo

There is a "tsp-location.yaml" file under "<project-path>" folder.
Read value of `directory` property in this file.
This is the relative path of the TypeSpec project in specs repo.

"<tsp-path>" refers to the full path of this TypeSpec project folder in specs repo.

### Regenerate the project in sdk repo

Update the "tsp-location.yaml" file, update the `commit` property to "<specs-main-commit>".

Run `tsp-client update` command under "<project-path>" folder to regenerate the project.

If the generate succeeded, commit the changes in "<project-path>" folder. And we have fixed this project.

If the generate fails, continue to next step.

### Fix the TypeSpec project in specs repo

All the fixes should be done under "<tsp-path>" folder.

See [common errors and their fixes](./common-error.md) to fix common errors.

When finished, check if the specs repo is on "main" branch. If yes, create a new branch, and create a pull request to "main" branch of specs repo.

Commit the changes in "<tsp-path>" folder. Note the commit hash on the current branch, call it "<specs-fix-commit>".

Repeat step "Regenerate the project in sdk repo", but use "<specs-fix-commit>" to update `commit` property in "tsp-location.yaml" file.

If it still fails, repeat this step until the generate succeeds.
