import axios from 'axios';
import { QUESTIONS_ADD_URL, GET_ALL_QUESTIONS_URL } from '../api/requests';

export const POST_NEW_QUESTION = 'POST_NEW_QUESTION';
export const GET_ALL_QUESTIONS = 'GET_ALL_QUESTIONS';

export const postNewQuestion = (postBody) => {
  const request = axios
    .post(QUESTIONS_ADD_URL, postBody)
    .catch(function (error) {
      console.log(error);
    });

  return {
    type: POST_NEW_QUESTION,
    payload: request,
  };
};

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
