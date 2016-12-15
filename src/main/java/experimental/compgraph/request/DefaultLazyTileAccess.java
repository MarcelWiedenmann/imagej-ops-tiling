package experimental.compgraph.request;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.imglib2.Interval;
import net.imglib2.Point;
import net.imglib2.RandomAccess;
import net.imglib2.RandomAccessible;
import net.imglib2.RandomAccessibleInterval;
import net.imglib2.Sampler;

public class DefaultLazyTileAccess<T> extends Point implements LazyTileAccess<T> {

	private final TilingRequestable<T> req;
	private final List<TilesRequest> intervals;

	private boolean requested = false;

	private final long[] gridDims;
	private final long[] dims;
	private final long[] tileDims;

	// TODO use LONG list here!!!
	private final List<RandomAccessible<T>> tiles;

	public DefaultLazyTileAccess(final TilesRequest intervals, final TilingRequestable<T> req, final long[] dims,
			final long[] tileDims) {
		super(1);

		this.req = req;
		this.intervals = intervals;
		this.dims = dims;

		//
		this.tileDims = new long[dims.length];
		this.gridDims = new long[n];
		this.tiles = new ArrayList<>();

		//
		for (int d = 0; d < n; ++d) {
			gridDims[d] = (dims[d] - 1) / tileDims[d] + 1;
		}
	}

	@Override
	public RandomAccessible<T> get() {
		synchronized (this) {
			if (!requested) {
				request();
				requested = true;
			}
		}

		return tiles.get((int) position[0]);
	}

	private void request() {
		final Map<Long, RandomAccessibleInterval<T>> requests = new HashMap<>();

		for (final TilesRequest req : intervals) {
			for (final TileInfo info : findTiles(req.key())) {
				final Interval coverage = info.coverage;

				RandomAccessibleInterval<T> answer = requests.get(info.idx);
				if (answer != null) {
					final RandomAccessibleInterval<T> randomAccessibleInterval = null;
				}
			}
		}

		// here I have to fill the tiles...
	}

	private Iterable<TileInfo> findTiles(final Interval key) {
		return null;
	}

	@Override
	public RandomAccess<RandomAccessible<T>> copyRandomAccess() {
		return null;
	}

	@Override
	public Sampler<RandomAccessible<T>> copy() {
		return null;
	}

	private class TileInfo {
		Interval coverage;
		long idx;
	}

}
