package io.github.plipp.openapi.diff.compare;

import static io.github.plipp.openapi.diff.utils.ChangedUtils.isChanged;

import io.github.plipp.openapi.diff.model.ChangedApiResponse;
import io.github.plipp.openapi.diff.model.ChangedResponse;
import io.github.plipp.openapi.diff.model.DiffContext;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/** Created by adarsh.sharma on 04/01/18. */
public class ApiResponseDiff {
  private OpenApiDiff openApiDiff;

  public ApiResponseDiff(OpenApiDiff openApiDiff) {
    this.openApiDiff = openApiDiff;
  }

  public Optional<ChangedApiResponse> diff(
      ApiResponses left, ApiResponses right, DiffContext context) {
    MapKeyDiff<String, ApiResponse> responseMapKeyDiff = MapKeyDiff.diff(left, right);
    List<String> sharedResponseCodes = responseMapKeyDiff.getSharedKey();
    Map<String, ChangedResponse> resps = new LinkedHashMap<>();
    for (String responseCode : sharedResponseCodes) {
      openApiDiff
          .getResponseDiff()
          .diff(left.get(responseCode), right.get(responseCode), context)
          .ifPresent(changedResponse -> resps.put(responseCode, changedResponse));
    }
    ChangedApiResponse changedApiResponse =
        new ChangedApiResponse(left, right, context)
            .setIncreased(responseMapKeyDiff.getIncreased())
            .setMissing(responseMapKeyDiff.getMissing())
            .setChanged(resps);
    openApiDiff
        .getExtensionsDiff()
        .diff(left.getExtensions(), right.getExtensions(), context)
        .ifPresent(changedApiResponse::setExtensions);
    return isChanged(changedApiResponse);
  }
}
