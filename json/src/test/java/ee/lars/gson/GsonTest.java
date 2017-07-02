package ee.lars.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import ee.lars.json.BagOfPrimitives;
import ee.lars.json.BagWithNull;
import ee.lars.json.BagWithTransientField;
import ee.lars.json.Card;
import ee.lars.json.ImmutableBag;
import ee.lars.moshi.*;
import org.junit.Before;
import org.junit.Test;

import static ee.lars.json.Suit.HEARTS;
import static org.assertj.core.api.Assertions.assertThat;

public class GsonTest {

    private Gson gson;

    @Before
    public void initialize() throws Exception {
        this.gson = new Gson();
    }

    @Test
    public void converts_primitive_int() throws Exception {
        // given

        // when
        final String json = gson.toJson(1);

        // then
        assertThat(json).isEqualTo("1");
    }

    @Test
    public void converts_simple_string() throws Exception {
        // given

        // when
        final String json = gson.toJson("hello world");

        // then
        assertThat(json).isEqualTo("\"hello world\"");
    }

    @Test
    public void converts_primitive_array() throws Exception {
        // given
        int[] values = {1, 2, 3};

        // when
        final String json = gson.toJson(values);

        // then
        assertThat(json).isEqualTo("[1,2,3]");
    }

    @Test
    public void converts_bag_of_primitives() throws Exception {
        // given
        BagOfPrimitives obj = new BagOfPrimitives();

        // when
        String json = gson.toJson(obj);

        // then
        assertThat(json).isEqualTo("{\"value1\":1,\"value2\":\"abc\",\"value3\":0}");
    }

    @Test
    public void converts_bag_with_final_field() throws Exception {
        // given
        ImmutableBag obj = new ImmutableBag("Oskar");

        // when
        String json = gson.toJson(obj);

        // then
        assertThat(json).isEqualTo("{\"name\":\"Oskar\"}");
    }

    @Test
    public void converts_json_to_bag_with_final_field() throws Exception {
        // given
        String json = "{\"name\":\"Oskar\"}";

        // when
        final ImmutableBag obj = gson.fromJson(json, ImmutableBag.class);

        // then
        assertThat(obj.getName()).isEqualTo("Oskar");
    }

    @Test
    public void ignores_transient_fields() throws Exception {
        // given
        BagWithTransientField obj = new BagWithTransientField();

        // when
        String json = gson.toJson(obj);

        // then
        assertThat(json).isEqualTo("{\"value1\":\"hello\"}");
    }

    @Test
    public void ignores_null_fields() throws Exception {
        // given
        BagWithNull obj = new BagWithNull();

        // when
        String json = gson.toJson(obj);

        // then
        assertThat(json).isEqualTo("{\"value1\":\"hello\"}");
    }

    @Test
    public void parses_null_to_null() throws Exception {
        // given
        String json = null;

        // when
        BagOfPrimitives obj = gson.fromJson(json, BagOfPrimitives.class);

        // then
        assertThat(obj).isNull();
    }

    @Test
    public void parses_empty_string_to_null() throws Exception {
        // given
        String json = "";

        // when
        BagOfPrimitives obj = gson.fromJson(json, BagOfPrimitives.class);

        // then
        assertThat(obj).isNull();
    }

    @Test
    public void parses_empty_json_string_to_default_primitive_or_null_for_non_primitives() throws Exception {
        // given
        String json = "{}";

        // when
        BagOfPrimitives obj = gson.fromJson(json, BagOfPrimitives.class);

        // then
        assertThat(obj).isNotNull();
        assertThat(obj.getValue3()).isEqualTo(0);
        assertThat(obj.getValue4()).isNull();
    }

    @Test
    public void custom_type_adapter() throws Exception {
        // given
        gson = new GsonBuilder().registerTypeAdapter(Card.class, new CardAdapter()).create();
        Card sixOfHearts = new Card('6', HEARTS);

        // when
        final String json = gson.toJson(sixOfHearts);

        // then
        assertThat(json).isEqualTo("\"6H\"");
    }
}
