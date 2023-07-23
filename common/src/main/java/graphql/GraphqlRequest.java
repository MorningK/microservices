package graphql;

import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class GraphqlRequest {
  private String operationName;
  private String query;
  private Map<String, Object> variables;
}
