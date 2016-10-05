
package experimental.tiling;

import net.imglib2.RandomAccessibleInterval;

import experimental.tiling.execution.LazyExecution;
import experimental.tiling.execution.LazyExecutionBranch;

public class CachedRandomAccessibleInterval<I, O> extends
	LazyExecution<RandomAccessibleInterval<I>, RandomAccessibleInterval<O>>
{

	public CachedRandomAccessibleInterval(final RandomAccessibleInterval<I> input,
		final LazyExecutionBranch<RandomAccessibleInterval<I>, RandomAccessibleInterval<O>> branch)
	{
		super(input, branch);
	}
}