package com.azure.autorest.extension.base.model.codemodel;

import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.nodes.MappingNode;
import org.yaml.snakeyaml.nodes.Node;
import org.yaml.snakeyaml.nodes.NodeId;
import org.yaml.snakeyaml.nodes.NodeTuple;
import org.yaml.snakeyaml.nodes.ScalarNode;
import org.yaml.snakeyaml.nodes.SequenceNode;

import java.util.ArrayList;
import java.util.List;

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
            } else if (type.equals(SerializationStyle.class)) {
                return SerializationStyle.fromValue(((ScalarNode) node).getValue());
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
                    case "binaries": {
                        SequenceNode value = (SequenceNode) tuple.getValueNode();
                        value.setListType(BinarySchema.class);
                        break;
                    }
                    case "booleans": {
                        SequenceNode value = (SequenceNode) tuple.getValueNode();
                        value.setListType(BooleanSchema.class);
                        break;
                    }
                    case "bytearrays": {
                        SequenceNode value = (SequenceNode) tuple.getValueNode();
                        value.setListType(ByteArraySchema.class);
                        break;
                    }
                    case "numbers": {
                        SequenceNode value = (SequenceNode) tuple.getValueNode();
                        value.setListType(NumberSchema.class);
                        break;
                    }
                    case "uris": {
                        SequenceNode value = (SequenceNode) tuple.getValueNode();
                        value.setListType(UriSchema.class);
                        break;
                    }
                    case "immediate":
                    case "all":
                        if (tuple.getValueNode() instanceof SequenceNode) {
                            SequenceNode value = (SequenceNode) tuple.getValueNode();
                            for (Node item : value.getValue()) {
                                item.setType(getSchemaTypeFromMappingNode((MappingNode) item));
                            }
                            break;
                        } else if (tuple.getValueNode() instanceof MappingNode) {
                            MappingNode value = (MappingNode) tuple.getValueNode();
                            for (NodeTuple item : value.getValue()) {
                                item.getValueNode().setType(getSchemaTypeFromMappingNode((MappingNode) (item.getValueNode())));
                            }
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
                        break;
                    }
                    case "extensions": {
                        MappingNode value = (MappingNode) tuple.getValueNode();
                        List<NodeTuple> actualValues = new ArrayList<>();
                        for (NodeTuple extension : value.getValue()) {
                            ScalarNode keyNode = (ScalarNode) extension.getKeyNode();
                            if ("x-ms-pageable".equals(keyNode.getValue())) {
                                actualValues.add(new NodeTuple(new ScalarNode(
                                        keyNode.getTag(),
                                        "xmsPageable",
                                        keyNode.getStartMark(),
                                        keyNode.getEndMark(),
                                        keyNode.getScalarStyle()),
                                        extension.getValueNode()));
                            } else if ("x-ms-skip-url-encoding".equals(keyNode.getValue())) {
                                actualValues.add(new NodeTuple(new ScalarNode(
                                        keyNode.getTag(),
                                        "xmsSkipUrlEncoding",
                                        keyNode.getStartMark(),
                                        keyNode.getEndMark(),
                                        keyNode.getScalarStyle()),
                                        extension.getValueNode()));
                            } else if ("x-ms-client-flatten".equals(keyNode.getValue())) {
                                actualValues.add(new NodeTuple(new ScalarNode(
                                        keyNode.getTag(),
                                        "xmsClientFlatten",
                                        keyNode.getStartMark(),
                                        keyNode.getEndMark(),
                                        keyNode.getScalarStyle()),
                                        extension.getValueNode()));
                            } else if ("x-ms-long-running-operation".equals(keyNode.getValue())) {
                                actualValues.add(new NodeTuple(new ScalarNode(
                                        keyNode.getTag(),
                                        "xmsLongRunningOperation",
                                        keyNode.getStartMark(),
                                        keyNode.getEndMark(),
                                        keyNode.getScalarStyle()),
                                        extension.getValueNode()));
                            } else if ("x-ms-flattened".equals(keyNode.getValue())) {
                                actualValues.add(new NodeTuple(new ScalarNode(
                                        keyNode.getTag(),
                                        "xmsFlattened",
                                        keyNode.getStartMark(),
                                        keyNode.getEndMark(),
                                        keyNode.getScalarStyle()),
                                        extension.getValueNode()));
                            } else if ("x-ms-azure-resource".equals(keyNode.getValue())) {
                                actualValues.add(new NodeTuple(new ScalarNode(
                                        keyNode.getTag(),
                                        "xmsAzureResource",
                                        keyNode.getStartMark(),
                                        keyNode.getEndMark(),
                                        keyNode.getScalarStyle()),
                                        extension.getValueNode()));
                            }
                        }
                        value.setValue(actualValues);
                        break;
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
                    case "group": return ObjectSchema.class;
                    case "integer": return NumberSchema.class;
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
