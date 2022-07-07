const tagKey = Symbol();
// TODO: How to NOT export but still use them internally?
export function $tag(context, target, tag) {
    let existingTags = context.program.stateMap(tagKey).get(target) || [];
    context.program.stateMap(tagKey).set(target, existingTags.concat([tag]));
}
// TODO: How to NOT export but still use them internally?
export function $isTaggedWith(context, _, target, tag, message) {
    let tags = context.program.stateMap(tagKey).get(target) || [];
    if (tags.indexOf(tag) === -1) {
        context.program.reportDiagnostic({
            code: "azure-missing-tag",
            severity: "error",
            message: message,
            target: target,
        });
        return;
    }
}
export const namespace = "Azure.Language.Private";
//# sourceMappingURL=internal-decorators.js.map
