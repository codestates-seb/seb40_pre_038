import { GET_SEARCH_LIST } from '../_actions/search_action';

const initialState = {
  searchInputValue: '',
  sortTabValue: 'newest',
  pageSizerValue: 15,
  pagerValue: 1,
  totalElements: 0,
  totalPages: 1,
  questionsList: [],
};

export const search = (state = initialState, action) => {
  switch (action.type) {
    case GET_SEARCH_LIST:
      return { ...state, ...action.payload };
    default:
      return state;
  }
};
