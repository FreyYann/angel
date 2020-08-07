/*
 * Tencent is pleased to support the open source community by making Angel available.
 *
 * Copyright (C) 2017-2018 THL A29 Limited, a Tencent company. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 *
 * https://opensource.org/licenses/Apache-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 *
 */
package com.tencent.angel.ml.math2.matrix;

import com.tencent.angel.ml.math2.storage.IntFloatSparseVectorStorage;
import com.tencent.angel.ml.math2.vector.IntFloatVector;
import com.tencent.angel.ml.math2.vector.Vector;
import it.unimi.dsi.fastutil.floats.FloatArrayList;
import it.unimi.dsi.fastutil.ints.IntArrayList;

public class CooIntFloatMatrix extends CooIntMatrix {

  protected float [] values;

  public CooIntFloatMatrix() {
    super();
  }

  public CooIntFloatMatrix(int matrixId, int clock, int[] rowIndices, int[] colIndices, float[] values, int[] shape) {
    this.matrixId = matrixId;
    this.clock = clock;
    this.rowIndices = rowIndices;
    this.colIndices = colIndices;
    this.values = values;
    this.shape = shape;
  }

  public CooIntFloatMatrix(int[] rowIndices, int[] colIndices, float[] values, int[] shape) {
    this(0, 0, rowIndices, colIndices, values, shape);
  }

  @Override
  public Vector getRow(int idx) {
    IntArrayList cols = new IntArrayList();
    FloatArrayList data = new FloatArrayList();
    for (int i = 0; i < rowIndices.length; i++) {
      if (rowIndices[i] == idx) {
        cols.add(colIndices[i]);
        data.add(values[i]);
      }
    }

    IntFloatSparseVectorStorage storage = new IntFloatSparseVectorStorage(shape[1], cols.toIntArray(), data.toFloatArray());
    return new IntFloatVector(getMatrixId(), idx, getClock(), shape[1], storage);
  }


  @Override
  public Vector getCol(int idx) {
    IntArrayList cols = new IntArrayList();
    FloatArrayList data = new FloatArrayList();
    for (int i = 0; i < colIndices.length; i++) {
      if (colIndices[i] == idx) {
        cols.add(rowIndices[i]);
        data.add(values[i]);
      }
    }

    IntFloatSparseVectorStorage storage = new IntFloatSparseVectorStorage(shape[0], cols.toIntArray(), data.toFloatArray());
    return new IntFloatVector(getMatrixId(), 0, getClock(), shape[0], storage);
  }

  public float[] getValues() {
    return values;
  }

  @Override
  public void clear() {
    matrixId = 0;
    clock = 0;
    rowIndices = null;
    colIndices = null;
    values = null;
  }

  @Override
  public Matrix copy() {
    float[] newData = new float[values.length];
    System.arraycopy(values, 0, newData, 0, values.length);
    return new CooIntFloatMatrix(matrixId, clock, rowIndices, colIndices, newData, shape);
  }

  @Override
  public double min() {
    throw new UnsupportedOperationException("this operation is not support!");
  }

  @Override
  public double max() {
    throw new UnsupportedOperationException("this operation is not support!");
  }

  @Override
  public double sum() {
    throw new UnsupportedOperationException("this operation is not support!");
  }

  @Override
  public double std() {
    throw new UnsupportedOperationException("this operation is not support!");
  }

  @Override
  public double average() {
    throw new UnsupportedOperationException("this operation is not support!");
  }

  @Override
  public double norm() {
    throw new UnsupportedOperationException("this operation is not support!");
  }

}
