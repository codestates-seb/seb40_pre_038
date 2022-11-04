import { GET_ALL_QUESTIONS } from '../_actions/search_action';

const initialState = {
  allQuestions: {
    sortTabValue: 'newest',
    pageSizerValue: 15,
    pagerValue: 1,
    totalElements: 0,
    totalPages: 1,
    questionsList: [],
  },
};

export function search(state = initialState, action) {
  switch (action.type) {
    case GET_ALL_QUESTIONS: // Questions - All Questions
      return {
        ...state,
        allQuestions: { ...state.allQuestions, ...action.payload },
      };
    default:
      return state;
  }
}
