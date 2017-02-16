package utils;

import java.util.Iterator;

/**
 * Author: timofeevdv Date: 01.02.2017
 */
public class OneWayList<E> {

	private Integer position = null;
	private Node lastNode;
	private Node firstNode;

	class Node {
		private final E element;
		private Node next;

		public Node(E element, Node next) {
			this.element = element;
			this.next = next;
		}

		public E getData() {
			return this.element;
		}
	}

	public class ListItr implements Iterator<E> {

		protected Integer position = 0;

		@Override
		public boolean hasNext() {
			return getNode(this.position) != null;
		}

		@Override
		public E next() {
			final Node retNode = getNode(position);
			this.position++;
			return retNode.getData();
		}

		public void insert(E element) {
			final Node prevNode = getNode(position - 1);

			if (prevNode == null) {
				final Node newNode = new Node(element, null);
				firstNode = newNode;
				lastNode = newNode;
				this.position = 0;
				return;
			}
			final Node newNode = new Node(element, prevNode.next);

			prevNode.next = newNode;
		}
	}

	public ListItr listIterator() {
		return new ListItr();
	}

	/**
	 * Добавляет элемент в конец списка
	 *
	 * @param element
	 *            Добавляемый элемент
	 */
	public void add(E element) {
		final Node newNode = new Node(element, null);
		if (this.firstNode == null) {
			// Список пуст
			this.firstNode = newNode;
			this.lastNode = newNode;
			this.position = 0;
		} else {
			this.lastNode.next = newNode;
			this.lastNode = newNode;
			this.position++;
		}
	}

	/**
	 * Вставляет новый элемент на заданную позицию
	 *
	 * @param element
	 *            Вставляемый элемент
	 * @param position
	 *            Позиция, на которую вставляется элемент
	 */
	public void insert(E element, Integer position) {
		checkPosition(position);
		Integer i = 0;
		Node prevNode = this.firstNode;
		while (i < position) {
			prevNode = this.getNode(i);
			i++;
		}
		final Node newNode = new Node(element, prevNode.next);
		prevNode.next = newNode;
	}

	/**
	 * Проверяет корректность позиции: значение позиции должно быть от 0 до <длина списка> - 1
	 *
	 * @param position
	 */
	private void checkPosition(Integer position) {
		if (position == null) {
			throw new IllegalArgumentException("Позиция не может быть равна null");
		}

		if (position < 0) {
			throw new IllegalArgumentException("Позиция не может быть меньше 0");
		}

		if (position > this.getSize()) {
			throw new IllegalArgumentException("Позиция не может быть больше длины списка");
		}
	}

	/**
	 * Возвращает количество элементов в списке
	 */
	public Integer getSize() {
		Integer size = 0;
		Node node = this.firstNode;
		while (node != null) {
			node = node.next;
			size++;
		}
		return size;
	}

	/**
	 * Возвращает элемент списка по заданному индексу
	 *
	 * @param position
	 *            позиция элемента. Может принимать значения [0..<длина списка>)
	 * @return Элемент типа E
	 */
	public E get(Integer position) {
		return getNode(position).getData();
	}

	/**
	 * Возвращает узел в заданной позиции
	 *
	 * @param position
	 *            Позиция узла
	 */
	private Node getNode(Integer position) {
		Integer i = 0;
		Node seek = this.firstNode;
		while (i < position && seek != null) {
			i++;
			seek = seek.next;
		}
		return seek;
	}

}
