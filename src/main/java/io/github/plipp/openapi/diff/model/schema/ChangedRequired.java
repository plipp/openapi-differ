package io.github.plipp.openapi.diff.model.schema;

import io.github.plipp.openapi.diff.model.ChangedList;
import io.github.plipp.openapi.diff.model.DiffContext;
import io.github.plipp.openapi.diff.model.DiffResult;
import java.util.List;
import lombok.Getter;

@Getter
public class ChangedRequired extends ChangedList<String> {

  public ChangedRequired(List<String> oldValue, List<String> newValue, DiffContext context) {
    super(oldValue, newValue, context);
  }

  @Override
  public DiffResult isItemsChanged() {
    if (context.isRequest() && getIncreased().isEmpty()
        || context.isResponse() && getMissing().isEmpty()) {
      return DiffResult.COMPATIBLE;
    }
    return DiffResult.INCOMPATIBLE;
  }
}
