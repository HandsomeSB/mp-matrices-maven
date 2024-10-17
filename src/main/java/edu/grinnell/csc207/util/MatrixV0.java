package edu.grinnell.csc207.util;

/**
 * An implementation of two-dimensional matrices.
 *
 * @author Your Name Here
 * @author Samuel A. Rebelsky
 *
 * @param <T>
 *   The type of values stored in the matrix.
 */
public class MatrixV0<T> implements Matrix<T> {
  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+
  T[][] val;
  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Create a new matrix of the specified width and height with the
   * given value as the default.
   *
   * @param width
   *   The width of the matrix.
   * @param height
   *   The height of the matrix.
   * @param def
   *   The default value, used to fill all the cells.
   *
   * @throws NegativeArraySizeException
   *   If either the width or height are negative.
   */
  @SuppressWarnings("unchecked")
  public MatrixV0(int width, int height, T def) {
    this.val = (T[][]) new Object[height][width];
    fillLine(0, 0, 1, 1, height, width, def);
  } // MatrixV0(int, int, T)

  /**
   * Create a new matrix of the specified width and height with
   * null as the default value.
   *
   * @param width
   *   The width of the matrix.
   * @param height
   *   The height of the matrix.
   *
   * @throws NegativeArraySizeException
   *   If either the width or height are negative.
   */
  public MatrixV0(int width, int height) {
    this(width, height, null);
  } // MatrixV0

  // +--------------+------------------------------------------------
  // | Core methods |
  // +--------------+

  /**
   * Get the element at the given row and column.
   *
   * @param row
   *   The row of the element.
   * @param col
   *   The column of the element.
   *
   * @return the value at the specified location.
   *
   * @throws IndexOutOfBoundsException
   *   If either the row or column is out of reasonable bounds.
   */
  public T get(int row, int col) {
    return this.val[row][col];
  } // get(int, int)

  /**
   * Set the element at the given row and column.
   *
   * @param row
   *   The row of the element.
   * @param col
   *   The column of the element.
   * @param val
   *   The value to set.
   *
   * @throws IndexOutOfBoundsException
   *   If either the row or column is out of reasonable bounds.
   */
  public void set(int row, int col, T val) {
    this.val[row][col] = val;
  } // set(int, int, T)

  /**
   * Determine the number of rows in the matrix.
   *
   * @return the number of rows.
   */
  public int height() {
    return this.val.length;
  } // height()

  /**
   * Determine the number of columns in the matrix.
   *
   * @return the number of columns.
   */
  public int width() {
    return this.val[0].length;
  } // width()

  /**
   * Insert a row filled with the default value.
   *
   * @param row
   *   The number of the row to insert.
   *
   * @throws IndexOutOfBoundsException
   *   If the row is negative or greater than the height.
   */
  @SuppressWarnings("unchecked")
  public void insertRow(int row) throws IndexOutOfBoundsException {
    try {
      this.insertRow(row, (T[]) new Object[this.width()]);
    } catch (ArraySizeException e) { }
    //STUB, throw IndexOutOfBoundsException
  } // insertRow(int)

  /**
   * Insert a row filled with the specified values.
   *
   * @param row
   *   The number of the row to insert.
   * @param vals
   *   The values to insert.
   *
   * @throws IndexOutOfBoundsException
   *   If the row is negative or greater than the height.
   * @throws ArraySizeException
   *   If the size of vals is not the same as the width of the matrix.
   */
  @SuppressWarnings("unchecked")
  public void insertRow(int row, T[] vals) throws ArraySizeException, IndexOutOfBoundsException {
    if(vals.length != this.width()) { 
      throw new ArraySizeException();
    }

    T[][] newMatrix = (T[][]) new Object[this.height() + 1][this.width()];
    for(int i = 0; i < row; ++i) { 
      newMatrix[i] = this.val[i];
    }
    for(int i = row + 1; i < newMatrix.length; ++i) { 
      newMatrix[i] = this.val[i - 1];
    }
    newMatrix[row] = vals;
    this.val = newMatrix;
  } // insertRow(int, T[])

  /**
   * Insert a column filled with the default value.
   *
   * @param col
   *   The number of the column to insert.
   *
   * @throws IndexOutOfBoundsException
   *   If the column is negative or greater than the width.
   */
  @SuppressWarnings("unchecked")
  public void insertCol(int col) throws IndexOutOfBoundsException{
    try {
      this.insertCol(col, (T[]) new Object[this.height()]);
    } catch (ArraySizeException e) {
      // It doesn't happen
    }

  } // insertCol(int)

  /**
   * Insert a column filled with the specified values.
   *
   * @param col
   *   The number of the column to insert.
   * @param vals
   *   The values to insert.
   *
   * @throws IndexOutOfBoundsException
   *   If the column is negative or greater than the width.
   * @throws ArraySizeException
   *   If the size of vals is not the same as the height of the matrix.
   */
  @SuppressWarnings("unchecked")
  public void insertCol(int col, T[] vals) throws ArraySizeException, IndexOutOfBoundsException {
    if(vals.length != this.height()) { 
      throw new ArraySizeException();
    }
    if(col < 0 || col > this.width()) { 
      throw new IndexOutOfBoundsException();
    }

    T[][] newMatrix = (T[][]) new Object[this.height()][this.width() + 1];

    for(int i = 0; i < this.height(); ++i) { 
      T[] newRow = (T[]) new Object[this.width() + 1];
      for(int j = 0; j < col; ++j) { 
        newRow[j] = this.val[i][j];
      }
      newRow[col] = vals[i];
      for(int j = col + 1; j < newMatrix[0].length; ++j) { 
        newRow[j] = this.val[i][j - 1];
      }
      newMatrix[i] = newRow;
    }

    this.val = newMatrix;
  } // insertCol(int, T[])

  /**
   * Delete a row.
   *
   * @param row
   *   The number of the row to delete.
   *
   * @throws IndexOutOfBoundsException
   *   If the row is negative or greater than or equal to the height.
   */
  @SuppressWarnings("unchecked")
  public void deleteRow(int row) throws IndexOutOfBoundsException{
    if(row < 0 || row >= this.height()) { 
      throw new IndexOutOfBoundsException();
    }

    T[][] newMatrix = (T[][]) new Object[this.height() - 1][this.width()];
    for(int i = 0; i < row; ++i) { 
      newMatrix[i] = this.val[i];
    }
    for(int i = row + 1; i < this.height(); ++i) { 
      newMatrix[i - 1] = this.val[i];
    }
    this.val = newMatrix;
  } // deleteRow(int)

  /**
   * Delete a column.
   *
   * @param col
   *   The number of the column to delete.
   *
   * @throws IndexOutOfBoundsException
   *   If the column is negative or greater than or equal to the width.
   */
  @SuppressWarnings("unchecked")
  public void deleteCol(int col) {
    if(col < 0 || col > this.width()) { 
      throw new IndexOutOfBoundsException();
    }

    T[][] newMatrix = (T[][]) new Object[this.height()][this.width() - 1];

    for(int i = 0; i < this.height(); ++i) { 
      T[] newRow = (T[]) new Object[this.width() - 1];
      for(int j = 0; j < col; ++j) { 
        newRow[j] = this.val[i][j];
      }

      for(int j = col + 1; j < newMatrix[0].length; ++j) { 
        newRow[j - 1] = this.val[i][j];
      }
      newMatrix[i] = newRow;
    }

    this.val = newMatrix;
  } // deleteCol(int)

  /**
   * Fill a rectangular region of the matrix.
   *
   * @param startRow
   *   The top edge / row to start with (inclusive).
   * @param startCol
   *   The left edge / column to start with (inclusive).
   * @param endRow
   *   The bottom edge / row to stop with (exclusive).
   * @param endCol
   *   The right edge / column to stop with (exclusive).
   * @param val
   *   The value to store.
   *
   * @throw IndexOutOfBoundsException
   *   If the rows or columns are inappropriate.
   */
  public void fillRegion(int startRow, int startCol, int endRow, int endCol,
      T val) {
    this.fillLine(startRow, startCol, 1, 1, endRow, endCol, val);
  } // fillRegion(int, int, int, int, T)

  /**
   * Fill a line (horizontal, vertical, diagonal).
   *
   * @param startRow
   *   The row to start with (inclusive).
   * @param startCol
   *   The column to start with (inclusive).
   * @param deltaRow
   *   How much to change the row in each step.
   * @param deltaCol
   *   How much to change the column in each step.
   * @param endRow
   *   The row to stop with (exclusive).
   * @param endCol
   *   The column to stop with (exclusive).
   * @param val
   *   The value to store.
   *
   * @throw IndexOutOfBoundsException
   *   If the rows or columns are inappropriate.
   */
  public void fillLine(int startRow, int startCol, int deltaRow, int deltaCol,
      int endRow, int endCol, T val) {
        for(int i = startRow; i < endRow; i+=deltaRow) { 
          for(int j = startCol; j < endCol; j+=deltaCol) { 
            this.val[i][j] = val;
          }
        }
  } // fillLine(int, int, int, int, int, int, T)

  /**
   * A make a copy of the matrix. May share references (e.g., if individual
   * elements are mutable, mutating them in one matrix may affect the other
   * matrix) or may not.
   *
   * @return a copy of the matrix.
   */
  public Matrix<T> clone() {
    Matrix<T> newMatrix = new MatrixV0<T>(this.width(), this.height());
    for(int i = 0; i < this.height(); ++i) { 
      for(int j = 0; j < this.width(); ++j) { 
        newMatrix.set(i, j, this.val[i][j]);
      }
    }
    return newMatrix;
  } // clone()

  /**
   * Determine if this object is equal to another object.
   *
   * @param other
   *   The object to compare.
   *
   * @return true if the other object is a matrix with the same width,
   * height, and equal elements; false otherwise.
   */
  @SuppressWarnings("unchecked")
  public boolean equals(Object other) {
    return other instanceof Matrix && this.equals((Matrix<T>) other);
  } // equals(Object)

  /**
   * Determine if this object is equal to another object.
   *
   * @param other
   *   The object to compare.
   *
   * @return true if the other object is a matrix with the same width,
   * height, and equal elements; false otherwise.
   */
  public boolean equals(Matrix other) {
    if(this.height() != other.height() || this.width() != other.width()) { 
      return false;
    }

    for(int i = 0; i < this.height(); ++i) { 
      for(int j = 0; j < this.width(); ++j) { 
        if(!this.val[i][j].equals(other.get(i, j))){
          return false;
        }
      }
    }
    return true;
  } // equals(Object)

  /**
   * Compute a hash code for this matrix. Included because any object
   * that implements `equals` is expected to implement `hashCode` and
   * ensure that the hash codes for two equal objects are the same.
   *
   * @return the hash code.
   */
  public int hashCode() {
    int multiplier = 7;
    int code = this.width() + multiplier * this.height();
    for (int row = 0; row < this.height(); row++) {
      for (int col = 0; col < this.width(); col++) {
        T val = this.get(row, col);
        if (val != null) {
          // It's okay if the following computation overflows, since
          // it will overflow uniformly.
          code = code * multiplier + val.hashCode();
        } // if
      } // for col
    } // for row
    return code;
  } // hashCode()
} // class MatrixV0
