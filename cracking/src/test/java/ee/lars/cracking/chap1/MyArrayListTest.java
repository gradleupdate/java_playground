package ee.lars.cracking.chap1;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MyArrayListTest {

    private List<String> list;
    private List<String> arrayList;

    @Before
    public void initialize() throws Exception {
        this.list = new MyArrayList<>();
        this.arrayList = new ArrayList<>();
    }

    @Test
    public void should_return_correct_size() throws Exception {
        // given
        this.list.add("hello");
        this.list.add("world");

        // then
        assertThat(this.list.size()).isEqualTo(2);
    }
    
    @Test
    public void new_list_is_empty() throws Exception {
        // then
        assertThat(this.list.isEmpty()).isTrue();
    }

    @Test
    public void add_and_remove_then_list_is_empty() throws Exception {
        // given
        this.list.add("hello");
        this.list.remove("hello");

        // then
        assertThat(this.list.isEmpty()).isTrue();
    }
    
    @Test
    public void after_adding_list_contains_entry() throws Exception {
        // given
        this.list.add("hello");
        this.list.add("world");

        // then
        assertThat(this.list.contains("hello")).isTrue();
    }

    @Test
    public void test_add_all() throws Exception {
        // given
        Collection<String> col = new ArrayList<>();
        col.add("hello");
        col.add("world");

        // when
        this.list.addAll(col);

        // then
        assertThat(this.list.contains("hello")).isTrue();
        assertThat(this.list.contains("world")).isTrue();
        assertThat(this.list.contains("hello")).isTrue();
    }

    @Test
    public void test_return_by_index() throws Exception {
        // given
        this.list.add("hello");
        this.list.add("world");

        // when
        final String itemZero = this.list.get(0);
        final String itemOne = this.list.get(1);

        // then
        assertThat(itemZero).isEqualTo("hello");
        assertThat(itemOne).isEqualTo("world");
    }
}