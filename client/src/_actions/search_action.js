import axios from 'axios';
import { GET_ALL_QUESTIONS_URL } from '../api/requests';

export const GET_SEARCH_LIST = 'GET_SEARCH_LIST';

export const getSearchList = (params) => {
  const { sortTabValue, pagerValue, pageSizerValue, searchInputValue } = params;
  const request = axios
    .get(
      `${GET_ALL_QUESTIONS_URL}/${sortTabValue}?page=${pagerValue}&&size=${pageSizerValue}`
    )
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
