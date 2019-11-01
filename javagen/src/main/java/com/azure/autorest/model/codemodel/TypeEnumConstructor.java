package com.azure.autorest.model.codemodel;

import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.nodes.MappingNode;
import org.yaml.snakeyaml.nodes.Node;
import org.yaml.snakeyaml.nodes.NodeId;
import org.yaml.snakeyaml.nodes.NodeTuple;
import org.yaml.snakeyaml.nodes.ScalarNode;
import org.yaml.snakeyaml.nodes.SequenceNode;

public class TypeEnumConstructor extends Constructor {
    public TypeEnumConstructor() {
        super();
        yamlClassConstructors.put(NodeId.scalar, new TypeEnumConstruct());
        yamlClassConstructors.put(NodeId.mapping, new TypeMapConstruct());
    }

    class TypeEnumConstruct extends Constructor.ConstructScalar {
        @Override
        public Object construct(Node node) {
            Class<?> type = node.getType();
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

    class TypeMapConstruct extends Constructor.ConstructMapping {
        @Override
        public Object construct(Node node) {
            MappingNode mappingNode = (MappingNode) node;
            for (NodeTuple tuple :  ((MappingNode) node).getValue()) {
                ScalarNode key = (ScalarNode) tuple.getKeyNode();
                switch (key.getValue()) {
                    case "objects": {
                        SequenceNode value = (SequenceNode) tuple.getValueNode();
                        value.setListType(ObjectSchema.class);
                        break;
                    }
                    case "choices": {
                        SequenceNode value = (SequenceNode) tuple.getValueNode();
                        value.setListType(ChoiceSchema.class);
                        break;
                    }
                    case "parameterGroups": {
                        SequenceNode value = (SequenceNode) tuple.getValueNode();
                        value.setListType(ParameterGroupSchema.class);
                        break;
                    }
                    case "sealedChoices": {
                        SequenceNode value = (SequenceNode) tuple.getValueNode();
                        value.setListType(SealedChoiceSchema.class);
                        break;
                    }
                    case "flags": {
                        SequenceNode value = (SequenceNode) tuple.getValueNode();
                        value.setListType(FlagSchema.class);
                        break;
                    }
//                    case "dictionaries": {
//                        SequenceNode value = (SequenceNode) tuple.getValueNode();
//                        value.setListType(Map.class);
//                        for (Node item : value.getValue()) {
//                            ((MappingNode) item).setTypes(String.class, Schema.class);
//                        }
//                        break;
//                    }
                    case "constants": {
                        SequenceNode value = (SequenceNode) tuple.getValueNode();
                        value.setListType(ConstantSchema.class);
                        break;
                    }
                    case "primitives": {
                        SequenceNode value = (SequenceNode) tuple.getValueNode();
                        value.setListType(Object.class);
                        break;
                    }
                    case "properties": {
                        SequenceNode value = (SequenceNode) tuple.getValueNode();
                        value.setListType(Property.class);
                        break;
                    }
                }
            }
            return super.construct(mappingNode);
        }
    }
}