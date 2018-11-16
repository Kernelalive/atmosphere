package eubrazil.atmosphere.rest.transfer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import eubrazil.atmosphere.service.transfer.Graphable;
import eubrazil.atmosphere.service.transfer.GraphableEdge;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.Serializable;
import java.util.SortedMap;

public class SankeyDataSeries implements Serializable {

  private static final Logger LOGGER = LoggerFactory.getLogger(SankeyDataSeries.class);
  private static final long serialVersionUID = 1L;

  @JsonSerialize(using = GraphableEdgeMapSerializer.class)
  private final SortedMap<GraphableEdge<Graphable>, Long> data;

  public SankeyDataSeries(final SortedMap<GraphableEdge<Graphable>, Long> data) {
    this.data = data;
  }

  public SortedMap<GraphableEdge<Graphable>, Long> getData() {
    return data;
  }

  static final class GraphableEdgeMapSerializer
      extends JsonSerializer<SortedMap<GraphableEdge<Graphable>, Long>> {

    @Override
    public void serialize(
        SortedMap<GraphableEdge<Graphable>, Long> arg0, JsonGenerator arg1, SerializerProvider arg2)
        throws IOException, JsonProcessingException {

      arg1.writeStartArray();

      arg0.entrySet()
          .forEach(
              entry -> {
                try {
                  arg1.writeStartObject();
                  arg1.writeStringField("from", entry.getKey().getLeft().getGraphKey());
                  arg1.writeStringField("to", entry.getKey().getRight().getGraphKey());
                  arg1.writeNumberField("weight", entry.getValue());
                  arg1.writeStringField("left", entry.getKey().getLeft().getName());
                  arg1.writeStringField("right", entry.getKey().getRight().getName());
                  arg1.writeEndObject();

                } catch (IOException e) {
                  LOGGER.error("Error in serializing GraphableEdge iterable", e);
                  throw new RuntimeException(e);
                }
              });

      arg1.writeEndArray();
    }
  }
}
