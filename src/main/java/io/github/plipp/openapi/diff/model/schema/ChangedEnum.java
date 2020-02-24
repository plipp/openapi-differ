package io.github.plipp.openapi.diff.model.schema;

import io.github.plipp.openapi.diff.model.ChangedList;
import io.github.plipp.openapi.diff.model.DiffContext;
import io.github.plipp.openapi.diff.model.DiffResult;
import java.util.List;
import lombok.Getter;

@Getter
public class ChangedEnum<T> extends ChangedList<T> {

  public ChangedEnum(List<T> oldValue, List<T> newValue, DiffContext context) {
    super(oldValue, newValue, context);
  }

  @Override
  public DiffResult isItemsChanged() {
    if (context.isRequest() && getMissing().isEmpty()
        || context.isResponse() && getIncreased().isEmpty()) {
      return DiffResult.COMPATIBLE;
    }
    return DiffResult.INCOMPATIBLE;
  }
}
