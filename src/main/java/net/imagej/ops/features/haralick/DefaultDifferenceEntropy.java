/*
 * #%L
 * ImageJ software for multidimensional image processing and analysis.
 * %%
 * Copyright (C) 2014 - 2016 Board of Regents of the University of
 * Wisconsin-Madison, University of Konstanz and Brian Northan.
 * %%
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * 
 * 1. Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDERS OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 * #L%
 */
package net.imagej.ops.features.haralick;

import net.imagej.ops.Ops;
import net.imagej.ops.features.haralick.helper.CoocPXMinusY;
import net.imagej.ops.special.function.Functions;
import net.imagej.ops.special.function.UnaryFunctionOp;
import net.imglib2.IterableInterval;
import net.imglib2.type.numeric.RealType;
import net.imglib2.type.numeric.real.DoubleType;

import org.scijava.plugin.Plugin;

/**
 * 
 * Implementation of Difference Entropy Haralick Feature
 * 
 * @author Andreas Graumann (University of Konstanz)
 * @author Christian Dietz (University of Konstanz)
 *
 */
@Plugin(type = Ops.Haralick.DifferenceEntropy.class, label = "Haralick: Difference Entropy")
public class DefaultDifferenceEntropy<T extends RealType<T>> extends
		AbstractHaralickFeature<T> implements Ops.Haralick.DifferenceEntropy {

	// Avoid log 0
	private static final double EPSILON = 0.00000001f;

	private UnaryFunctionOp<double[][], double[]> coocPXMinusYFunc;
	
	@Override
	public void initialize() {
		super.initialize();
		coocPXMinusYFunc = Functions.unary(ops(), CoocPXMinusY.class, double[].class, double[][].class);
	}
	
	@Override
	public void compute(final IterableInterval<T> input, final DoubleType output) {
		final double[][] matrix = getCooccurrenceMatrix(input);

		final double[] pxminusy = coocPXMinusYFunc.calculate(matrix);
		final int nrGrayLevels = matrix.length;

		double res = 0;
		for (int k = 0; k <= nrGrayLevels - 1; k++) {
			res += pxminusy[k] * Math.log(pxminusy[k] + EPSILON);
		}

		output.set(-res);

	}

}
