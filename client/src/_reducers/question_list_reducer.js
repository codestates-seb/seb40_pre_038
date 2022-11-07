import {
  GET_ALL_QUESTIONS,
  GET_TOP_QUESTIONS,
} from '../_actions/question_list_action';

const initialState = {
  allQuestions: {
    sortTabValue: 'newest',
    pageSizerValue: 15,
    pagerValue: 1,
    totalElements: 0,
    totalPages: 1,
    questionsList: [],
  },
  topQuestions: {
    sortTabValue: 'hot',
    questionsList: [],
  },
};

export function questionList(state = initialState, action) {
  switch (action.type) {
    case GET_ALL_QUESTIONS: // Questions - All Questions
      return {
        ...state,
        allQuestions: { ...state.allQuestions, ...action.payload },
      };
    case GET_TOP_QUESTIONS: // Home - Top Questions
      return {
        ...state,
        topQuestions: { ...state.topQuestions, ...action.payload },
      };
    default:
      return state;
  }
}
