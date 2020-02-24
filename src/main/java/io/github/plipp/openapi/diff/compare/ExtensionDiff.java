package io.github.plipp.openapi.diff.compare;

import io.github.plipp.openapi.diff.model.Change;
import io.github.plipp.openapi.diff.model.Changed;
import io.github.plipp.openapi.diff.model.DiffContext;

public interface ExtensionDiff {

  ExtensionDiff setOpenApiDiff(OpenApiDiff openApiDiff);

  String getName();

  Changed diff(Change extension, DiffContext context);

  default boolean isParentApplicable(
      Change.Type type, Object object, Object extension, DiffContext context) {
    return true;
  }
}
