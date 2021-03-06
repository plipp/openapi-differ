package io.github.plipp.openapi.test;

import static io.github.plipp.openapi.test.TestUtils.assertOpenApiChangedEndpoints;

import org.junit.jupiter.api.Test;

/** Created by adarsh.sharma on 03/01/18. */
public class ParameterDiffTest {
  private final String OPENAPI_DOC1 = "parameters_diff_1.yaml";
  private final String OPENAPI_DOC2 = "parameters_diff_2.yaml";

  @Test
  public void testDiffDifferent() {
    assertOpenApiChangedEndpoints(OPENAPI_DOC1, OPENAPI_DOC2);
  }
}
