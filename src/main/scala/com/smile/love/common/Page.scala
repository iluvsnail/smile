package com.smile.love.common

/**
 *
 * @author chenyl
 * @since 2017-07-26 11:02
 * @return
*/
class Page {
  private var size = 0
  private var offset = 0

  def getSize: Int = size

  def setSize(size: Int): Unit = {
    this.size = size
  }

  def getOffset: Int = offset

  def setOffset(offset: Int): Unit = {
    this.offset = offset
  }
}
