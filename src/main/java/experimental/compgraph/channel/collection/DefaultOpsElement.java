
package experimental.compgraph.channel.collection;

import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

import net.imglib2.util.Pair;

import experimental.compgraph.channel.OpsBoundedChannel;
import experimental.compgraph.channel.OpsChannel;

public class DefaultOpsElement<I> implements OpsElement<I> {

	private final Stream<I> source;

	public DefaultOpsElement(final I source) {
		this.source = Stream.of(source);
	}

	// -- OpsElement --

	@Override
	public <I2> OpsBoundedChannel<Pair<I, I2>> join(final OpsBoundedChannel<I2> c,
		final BiPredicate<? super I, ? super I2> f)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <I2> OpsBoundedChannel<Pair<I, I2>> cartesian(final OpsBoundedChannel<I2> c) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <O> OpsChannel<O> transform(final Function<? super OpsChannel<I>, OpsChannel<O>> f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <O> OpsElement<O> map(final Function<? super I, O> f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <O> OpsElement<O> map(final BiConsumer<? super I, ? extends Consumer<O>> f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OpsElement<I> filter(final Predicate<? super I> f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <O> OpsElement<? extends OpsBoundedChannel<O>> partition(
		final BiConsumer<? super I, ? extends Consumer<O>> f)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <O> OpsBoundedChannel<? extends OpsBoundedChannel<O>> group(final Function<? super I, Integer> f) {
		// TODO Auto-generated method stub
		return null;
	}
}
