import "@typespec/rest";

using TypeSpec.Http;
using TypeSpec.Rest;

@service({
  title: "Polymorphism",
  version: "1.0.0",
})
namespace Cadl.Polymorphism;

// discriminator
@discriminator("kind")
model Pet {
  name: string;
  weight?: float32;
}
model Cat extends Pet {
  kind: "cat";
  meow: int32;
}
model Dog extends Pet {
  kind: "dog";
  bark: string;
}

// discriminator with templated model
@discriminator("kind")
model Task {}
model TemplatedTask<TDiscriminator, TParameters> extends Task {
  kind: TDiscriminator;
  name: string;
  parameters?: TParameters;
}
model Parameter {}
model EntitiesTaskParameters extends Parameter {
  entity?: string;
}
model EntityTask is TemplatedTask<"EntityRecognition", EntitiesTaskParameters>;
// NOTE: avoid extends from templated model
// model EntityTask extends TemplatedTask<"EntityRecognition", EntitiesTaskParameters> {
// }

// extends
model BaseType {
  name: string;
}
model DerivedType extends BaseType {
  description?: string;
}
model DerivedTypeData extends BaseType {
  data: bytes;
}
model DerivedTypeList extends DerivedType {
  items: BaseType[];
}

@route("/Pets")
interface Polymorphism {
  read(): {
    @body body: Pet;
  };

  @put
  write(@body body: BaseType): BaseType;

  @post
  task(@body body: Task): Task;
}
