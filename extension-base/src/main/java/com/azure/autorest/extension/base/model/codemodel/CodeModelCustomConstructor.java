package com.azure.autorest.extension.base.model.codemodel;

import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.nodes.MappingNode;
import org.yaml.snakeyaml.nodes.Node;
import org.yaml.snakeyaml.nodes.NodeId;
import org.yaml.snakeyaml.nodes.NodeTuple;
import org.yaml.snakeyaml.nodes.ScalarNode;
import org.yaml.snakeyaml.nodes.SequenceNode;

public class CodeModelCustomConstructor extends Constructor {
    public CodeModelCustomConstructor() {
        super();
        yamlClassConstructors.put(NodeId.scalar, new TypeEnumConstruct());
        yamlClassConstructors.put(NodeId.mapping, new TypeMapConstruct());
    }

    class TypeEnumConstruct extends Constructor.ConstructScalar {
        @Override
        public Object construct(Node node) {
            Class<?> type = node.getType();
            if (type.equals(Schema.AllSchemaTypes.class)) {
                return Schema.AllSchemaTypes.fromValue(((ScalarNode) node).getValue());
            }/* else if (type.equals(ChoiceSchema.Type.class)) {
                return ChoiceSchema.Type.fromValue(((ScalarNode) node).getValue());
            } else if (type.equals(SealedChoiceSchema.Type.class)) {
                return SealedChoiceSchema.Type.fromValue(((ScalarNode) node).getValue());
            }*/ else if (type.equals(Parameter.ImplementationLocation.class)) {
                return Parameter.ImplementationLocation.fromValue(((ScalarNode) node).getValue());
            } else if (type.equals(DateTimeSchema.Format.class)) {
                return DateTimeSchema.Format.fromValue(((ScalarNode) node).getValue());
            } else if (type.equals(ByteArraySchema.Format.class)) {
                return ByteArraySchema.Format.fromValue(((ScalarNode) node).getValue());
            } else if (type.equals(RequestParameterLocation.class)) {
                return RequestParameterLocation.fromValue(((ScalarNode) node).getValue());
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
                    case "arrays": {
                        SequenceNode value = (SequenceNode) tuple.getValueNode();
                        value.setListType(ArraySchema.class);
                        break;
                    }
                    case "ands": {
                        SequenceNode value = (SequenceNode) tuple.getValueNode();
                        value.setListType(AndSchema.class);
                        break;
                    }
                    case "ors": {
                        SequenceNode value = (SequenceNode) tuple.getValueNode();
                        value.setListType(OrSchema.class);
                        break;
                    }
                    case "xors": {
                        SequenceNode value = (SequenceNode) tuple.getValueNode();
                        value.setListType(XorSchema.class);
                        break;
                    }
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
                    case "dictionaries": {
                        SequenceNode value = (SequenceNode) tuple.getValueNode();
                        value.setListType(DictionarySchema.class);
                        break;
                    }
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
                    case "allOf": {
                        SequenceNode value = (SequenceNode) tuple.getValueNode();
                        for (Node item : value.getValue()) {
                            item.setType(getSchemaTypeFromMappingNode((MappingNode) item));
                        }
                        break;
                    }
                    case "elementType":
                    case "valueType":
                    case "schema": {
                        MappingNode value = (MappingNode) tuple.getValueNode();
                        value.setType(getSchemaTypeFromMappingNode(value));
                    }
                }
            }
            return super.construct(mappingNode);
        }
    }

    private static Class<?> getSchemaTypeFromMappingNode(MappingNode value) {
        for (NodeTuple schemaProps : value.getValue()) {
            if (((ScalarNode) schemaProps.getKeyNode()).getValue().equals("type")) {
                switch (((ScalarNode) schemaProps.getValueNode()).getValue()) {
                    case "any": return AnySchema.class;
                    case "and": return AndSchema.class;
                    case "array": return ArraySchema.class;
                    case "boolean": return BooleanSchema.class;
                    case "binary": return BinarySchema.class;
                    case "byte-array": return ByteArraySchema.class;
                    case "char": return CharSchema.class;
                    case "choice": return ChoiceSchema.class;
                    case "constant": return ConstantSchema.class;
                    case "credential": return CredentialSchema.class;
                    case "date": return DateSchema.class;
                    case "date-time": return DateTimeSchema.class;
                    case "dictionary": return DictionarySchema.class;
                    case "duration": return DurationSchema.class;
                    case "flag": return FlagSchema.class;
                    case "integer": return PrimitiveSchema.class;
                    case "not": return NotSchema.class;
                    case "number": return NumberSchema.class;
                    case "object": return ObjectSchema.class;
                    case "odata-query": return ODataQuerySchema.class;
                    case "or": return OrSchema.class;
                    case "parameter-group": return ParameterGroupSchema.class;
                    case "sealed-choice": return SealedChoiceSchema.class;
                    case "string": return StringSchema.class;
                    case "unixtime": return UnixTimeSchema.class;
                    case "uri": return UriSchema.class;
                    case "uuid": return UuidSchema.class;
                    case "xor": return XorSchema.class;
                    default: return Schema.class;
                }
            }
        }
        return Schema.class;
    }
}