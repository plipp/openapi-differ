package io.github.plipp.openapi.test;

import static io.github.plipp.openapi.test.TestUtils.assertOpenApiAreEquals;
import static io.github.plipp.openapi.test.TestUtils.assertOpenApiChangedEndpoints;

import org.junit.jupiter.api.Test;

public class ArrayDiffTest {

  private final String OPENAPI_DOC31 = "array_diff_1.yaml";
  private final String OPENAPI_DOC32 = "array_diff_2.yaml";

  @Test
  public void testArrayDiffDifferent() {
    assertOpenApiChangedEndpoints(OPENAPI_DOC31, OPENAPI_DOC32);
  }

  @Test
  public void testArrayDiffSame() {
    assertOpenApiAreEquals(OPENAPI_DOC31, OPENAPI_DOC31);
  }
}
