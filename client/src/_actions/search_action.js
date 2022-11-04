import axios from 'axios';
import { GET_ALL_QUESTIONS_URL } from '../api/requests';

export const GET_ALL_QUESTIONS = 'GET_ALL_QUESTIONS';

// Questions - All Questions
export const getAllQuestions = (params) => {
  const { sortTabValue, pagerValue, pageSizerValue } = params;
  const request = axios
    .get(
      `${GET_ALL_QUESTIONS_URL}/${sortTabValue}?page=${pagerValue}&&size=${pageSizerValue}`
    )
    .then((response) => {
      const { pageInfo, data } = response.data;
      return {
        sortTabValue: sortTabValue,
        pageSizerValue: pageInfo.size,
        pagerValue: pageInfo.page,
        totalElements: pageInfo.totalElements,
        totalPages: pageInfo.totalPages,
        questionsList: data,
      };
    });

  return {
    type: GET_ALL_QUESTIONS,
    payload: request,
  };
};
