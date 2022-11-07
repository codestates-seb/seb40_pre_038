import axios from '../api/axios';
import { GET_ALL_QUESTIONS_URL, GET_TOP_QUESTIONS_URL } from '../api/requests';

export const GET_ALL_QUESTIONS = 'GET_ALL_QUESTIONS';
export const GET_TOP_QUESTIONS = 'GET_TOP_QUESTIONS';

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

// Home - Top Questions
export const getTopQuestions = (sortTabValue) => {
  const request = axios
    .get(`${GET_TOP_QUESTIONS_URL}/${sortTabValue}`)
    .then((response) => {
      const { data } = response.data;
      return {
        sortTabValue: sortTabValue,
        questionsList: data,
      };
    });

  return {
    type: GET_TOP_QUESTIONS,
    payload: request,
  };
};
