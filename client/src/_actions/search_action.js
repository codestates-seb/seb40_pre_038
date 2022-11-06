import axios from '../api/axios';
import { SEARCH_URL } from '../api/requests';

export const GET_SEARCH_LIST = 'GET_SEARCH_LIST';

export const getSearchList = (params) => {
  const { sortTabValue, pagerValue, pageSizerValue, searchInputValue } = params;
  if (searchInputValue.length === 0)
    return {
      type: GET_SEARCH_LIST,
      payload: {
        searchInputValue: searchInputValue,
        sortTabValue: sortTabValue,
        pageSizerValue: pageSizerValue,
        pagerValue: pagerValue,
        totalElements: 0,
        totalPages: 1,
        questionsList: [],
      },
    };

  const request = axios
    .get(`${SEARCH_URL}/${sortTabValue}`, {
      params: {
        page: pagerValue,
        size: pageSizerValue,
        q: encodeURIComponent(searchInputValue),
      },
    })
    .then((response) => {
      const { pageInfo, data } = response.data;
      return {
        searchInputValue: searchInputValue,
        sortTabValue: sortTabValue,
        pageSizerValue: pageInfo.size,
        pagerValue: pageInfo.page,
        totalElements: pageInfo.totalElements,
        totalPages: pageInfo.totalPages,
        questionsList: data,
      };
    });

  return {
    type: GET_SEARCH_LIST,
    payload: request,
  };
};
