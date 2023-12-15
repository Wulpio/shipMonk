package com.wulp;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Date;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class SortedLinkedListTest {

    @ParameterizedTest
    @MethodSource("provideParameters")
    void GIVEN_simple_input_value_WHEN_add_THEN_correct_answer_is_returned(Object inputValue, boolean returnValue, SortedLinkedList.Type type) {
        var list = new SortedLinkedList();

        var added = list.add(inputValue);

        assertThat(added).isEqualTo(returnValue);
        assertThat(list.getTypeOfList()).isEqualTo(type);
    }

    @Test
    void WHEN_adding_integer_after_string_THEN_string_is_added_but_integer_is_not_added() {
        var list = new SortedLinkedList();

        var added1 = list.add("someText");
        var added2 = list.add(1);

        assertThat(added1).isTrue();
        assertThat(list.getTypeOfList()).isEqualTo(SortedLinkedList.Type.STRING);
        assertThat(added2).isFalse();
        assertThat(list.getTypeOfList()).isEqualTo(SortedLinkedList.Type.STRING);
    }

    @Test
    void WHEN_adding_string_after_integer_THEN_integer_is_added_but_string_is_not_added() {
        var list = new SortedLinkedList();

        var added1 = list.add(1);
        var added2 = list.add("someText");

        assertThat(added1).isTrue();
        assertThat(list.getTypeOfList()).isEqualTo(SortedLinkedList.Type.INT);
        assertThat(added2).isFalse();
        assertThat(list.getTypeOfList()).isEqualTo(SortedLinkedList.Type.INT);
    }

    @Test
    void WHEN_adding_date_after_integer_THEN_integer_is_added_but_date_not() {
        var list = new SortedLinkedList();

        var added1 = list.add(1);
        var added2 = list.add(new Date());

        assertThat(added1).isTrue();
        assertThat(list.getTypeOfList()).isEqualTo(SortedLinkedList.Type.INT);

        assertThat(added2).isFalse();
        assertThat(list.getTypeOfList()).isEqualTo(SortedLinkedList.Type.INT);
    }

    @Test
    void WHEN_adding_3_sorted_strings_THEN_string_are_stored_in_sorted_order() {
        var list = new SortedLinkedList();

        var added1 = list.add("a");
        var added2 = list.add("b");
        var added3 = list.add("c");

        assertThat(added1).isTrue();
        assertThat(added2).isTrue();
        assertThat(added3).isTrue();

        assertThat(list.get(0)).isEqualTo("a");
        assertThat(list.get(1)).isEqualTo("b");
        assertThat(list.get(2)).isEqualTo("c");
    }

    @Test
    void WHEN_adding_3_unsorted_strings_THEN_string_are_stored_in_sorted_order() {
        var list = new SortedLinkedList();

        var added1 = list.add("c");
        var added2 = list.add("b");
        var added3 = list.add("a");

        assertThat(added1).isTrue();
        assertThat(added2).isTrue();
        assertThat(added3).isTrue();

        assertThat(list.get(0)).isEqualTo("a");
        assertThat(list.get(1)).isEqualTo("b");
        assertThat(list.get(2)).isEqualTo("c");
    }

    private static Stream<Arguments> provideParameters() {
        return Stream.of(
                Arguments.of("halo", true, SortedLinkedList.Type.STRING),
                Arguments.of("", true, SortedLinkedList.Type.STRING),
                Arguments.of("null", true, SortedLinkedList.Type.STRING),
                Arguments.of(1L, false, null),
                Arguments.of(1, true, SortedLinkedList.Type.INT),
                Arguments.of(1.000, false, null),
                Arguments.of(new Date(), false, null),
                Arguments.of(null, false, null)
        );
    }

}