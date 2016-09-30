
package experimental.tiling.execution;

import net.imagej.ops.special.function.UnaryFunctionOp;

// NB: Method calls are done in parent (within a tree: leaf-to-root) direction.
// Thus, supporting multiple parents (N:1) should be easy, (1:N) is not! Here, we need or "junction of branches"-approach.
public class LazyExecutionStep<I, O> implements LazyExecutionNode<I, O> {

	private final LazyExecutionNode<?, I> parent;
	private final UnaryFunctionOp<I, O> op;

	public LazyExecutionStep(final LazyExecutionNode<?, I> parent, final UnaryFunctionOp<I, O> op) {
		this.parent = parent;
		this.op = op;
	}

	private LazyExecutionStep(final LazyExecutionStep<I, O> node) {
		parent = node.getParent().copy();
		op = node.op;
	}

	// -- --

	@Override
	public LazyExecutionNode<?, I> getParent() {
		return parent;
	}

	@Override
	public LazyExecutionNode<?, ?> getRoot() {
		return parent != null ? parent.getRoot() : this;
	}

	@Override
	public O get() {
		return op.compute1(parent.get());
	}

	@Override
	public LazyExecutionStep<I, O> copy() {
		return new LazyExecutionStep<>(this);
	}
}
