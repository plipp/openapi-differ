package io.github.plipp.openapi.diff.compare.schemadiffresult;

import io.github.plipp.openapi.diff.compare.OpenApiDiff;
import io.github.plipp.openapi.diff.model.ChangedSchema;
import io.github.plipp.openapi.diff.model.DiffContext;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.media.ArraySchema;
import io.swagger.v3.oas.models.media.Schema;
import java.util.HashSet;
import java.util.Optional;

/** Created by adarsh.sharma on 18/12/17. */
public class ArraySchemaDiffResult extends SchemaDiffResult {
  public ArraySchemaDiffResult(OpenApiDiff openApiDiff) {
    super("array", openApiDiff);
  }

  @Override
  public <T extends Schema<X>, X> Optional<ChangedSchema> diff(
      HashSet<String> refSet,
      Components leftComponents,
      Components rightComponents,
      T left,
      T right,
      DiffContext context) {
    ArraySchema leftArraySchema = (ArraySchema) left;
    ArraySchema rightArraySchema = (ArraySchema) right;
    super.diff(refSet, leftComponents, rightComponents, left, right, context);
    openApiDiff
        .getSchemaDiff()
        .diff(
            refSet,
            leftArraySchema.getItems(),
            rightArraySchema.getItems(),
            context.copyWithRequired(true))
        .ifPresent(changedSchema::setItems);
    return isApplicable(context);
  }
}
