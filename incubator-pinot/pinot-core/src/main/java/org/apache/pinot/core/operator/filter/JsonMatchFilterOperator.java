/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.pinot.core.operator.filter;

import org.apache.pinot.core.operator.blocks.FilterBlock;
import org.apache.pinot.core.operator.docidsets.BitmapDocIdSet;
import org.apache.pinot.segment.spi.index.reader.JsonIndexReader;


/**
 * Filter operator for JSON_MATCH. E.g. SELECT ... WHERE JSON_MATCH(column_name, filter_string)
 */
public class JsonMatchFilterOperator extends BaseFilterOperator {
  private static final String OPERATOR_NAME = "JsonMatchFilterOperator";

  private final JsonIndexReader _jsonIndex;
  private final String _filterString;
  private final int _numDocs;

  public JsonMatchFilterOperator(JsonIndexReader jsonIndex, String filterString, int numDocs) {
    _jsonIndex = jsonIndex;
    _filterString = filterString;
    _numDocs = numDocs;
  }

  @Override
  protected FilterBlock getNextBlock() {
    return new FilterBlock(new BitmapDocIdSet(_jsonIndex.getMatchingDocIds(_filterString), _numDocs));
  }

  @Override
  public String getOperatorName() {
    return OPERATOR_NAME;
  }
}
