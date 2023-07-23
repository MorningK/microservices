package graphql;

import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class GraphqlResponse<T> {
  private T data;
  private List<GraphqlError> errors;

  @Getter
  @Setter
  @ToString
  static class GraphqlError {
    private String message;
    private Map<String, Object> extensions;
  }
}
