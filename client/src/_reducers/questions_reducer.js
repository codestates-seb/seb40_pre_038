import {
  POST_NEW_QUESTION,
  GET_ALL_QUESTIONS,
  GET_TOP_QUESTIONS,
} from '../_actions/questions_action';

export function questions(state = {}, action) {
  switch (action.type) {
    case POST_NEW_QUESTION:
      return null;
    default:
      return state;
  }
}

// Questions - All Questions
const allQuestionsInit = {
  sortTabValue: 'newest',
  pageSizerValue: 15,
  pagerValue: 1,
  totalElements: 0,
  totalPages: 1,
  questionsList: [],
};
export const allQuestions = (state = allQuestionsInit, action) => {
  switch (action.type) {
    case GET_ALL_QUESTIONS:
      return { ...state, ...action.payload };
    default:
      return state;
  }
};

// Home - Top Questions
const topQuestionsInit = {
  sortTabValue: 'hot',
  questionsList: [],
};
export const topQuestions = (state = topQuestionsInit, action) => {
  switch (action.type) {
    case GET_TOP_QUESTIONS:
      return { ...state, ...action.payload };
    default:
      return state;
  }
};
