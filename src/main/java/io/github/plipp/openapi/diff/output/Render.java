package io.github.plipp.openapi.diff.output;

import io.github.plipp.openapi.diff.model.ChangedOpenApi;

public interface Render {

  String render(ChangedOpenApi diff);
}
