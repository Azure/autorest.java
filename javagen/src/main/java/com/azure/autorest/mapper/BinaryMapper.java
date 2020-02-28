package com.azure.autorest.mapper;

import com.azure.autorest.extension.base.model.codemodel.BinarySchema;
import com.azure.autorest.model.clientmodel.GenericType;
import com.azure.autorest.model.clientmodel.IType;

public class BinaryMapper implements IMapper<BinarySchema, IType> {

  private static final BinaryMapper INSTANCE = new BinaryMapper();

  public static BinaryMapper getInstance() {
    return INSTANCE;
  }

  @Override
  public IType map(BinarySchema binarySchema) {
    if (binarySchema == null) {
      return null;
    }
    return GenericType.FluxByteBuffer;
  }
}
