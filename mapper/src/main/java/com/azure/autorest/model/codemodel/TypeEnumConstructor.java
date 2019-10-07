package com.azure.autorest.model.codemodel;

import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.nodes.Node;
import org.yaml.snakeyaml.nodes.NodeId;
import org.yaml.snakeyaml.nodes.ScalarNode;

public class TypeEnumConstructor extends Constructor {
    public TypeEnumConstructor() {
        yamlClassConstructors.put(NodeId.scalar, new TypeEnumConstruct());
    }

    class TypeEnumConstruct extends Constructor.ConstructScalar {
        @Override
        public Object construct(Node node) {
            Class type = node.getType();
            if (type.equals(ChoiceSchema.Type.class)) {
                return ChoiceSchema.Type.fromValue(((ScalarNode) node).getValue());
            } else if (type.equals(SealedChoiceSchema.Type.class)) {
                return SealedChoiceSchema.Type.fromValue(((ScalarNode) node).getValue());
            } else if (type.equals(Parameter.ImplementationLocation.class)) {
                return Parameter.ImplementationLocation.fromValue(((ScalarNode) node).getValue());
            } else {
                // create JavaBean
                return super.construct(node);
            }
        }
    }
}