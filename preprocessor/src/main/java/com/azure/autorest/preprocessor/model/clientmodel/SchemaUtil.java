package com.azure.autorest.preprocessor.model.clientmodel;

import com.azure.autorest.extension.base.model.codemodel.AnySchema;
import com.azure.autorest.extension.base.model.codemodel.ObjectSchema;
import com.azure.autorest.extension.base.model.codemodel.Schema;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class SchemaUtil {
    private SchemaUtil() {
    }

    // TODO: P3 support multiple inheritance
    public static Schema getLowestCommonParent(List<Schema> schemas) {
        if (schemas == null || schemas.isEmpty()) {
            return null;
        }
        LinkedList<Schema> chain = null;
        for (Schema schema : schemas) {
            if (chain == null) {
                chain = new LinkedList<>();
                chain.addFirst(schema);
                while (schema instanceof ObjectSchema
                        && ((ObjectSchema) schema).getParents() != null
                        && ((ObjectSchema) schema).getParents().getImmediate() != null
                        && !((ObjectSchema) schema).getParents().getImmediate().isEmpty()) {
                    // Assume always inheriting from an ObjectSchema and no multiple inheritance
                    schema = ((ObjectSchema) schema).getParents().getImmediate().get(0);
                    chain.addFirst(schema);
                }
            } else {
                Stack<Schema> newChain = new Stack<>();
                newChain.push(schema);
                while (schema instanceof ObjectSchema
                        && ((ObjectSchema) schema).getParents() != null
                        && ((ObjectSchema) schema).getParents().getImmediate() != null
                        && !((ObjectSchema) schema).getParents().getImmediate().isEmpty()) {
                    // Assume always inheriting from an ObjectSchema and no multiple inheritance
                    schema = ((ObjectSchema) schema).getParents().getImmediate().get(0);
                    newChain.push(schema);
                }
                int i = 0;
                while (!newChain.empty() && i < chain.size()) {
                    Schema top = chain.get(i);
                    Schema compare = newChain.pop();
                    if (top == compare) {
                        i++;
                    } else {
                        for (; i < chain.size(); i++) {
                            chain.remove(i);
                        }
                    }
                }
            }
        }
        return chain.isEmpty() ? new AnySchema() : chain.getLast();
    }
}
