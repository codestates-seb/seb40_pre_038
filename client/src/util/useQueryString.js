import { useLocation } from 'react-router-dom';

const useQueryString = () => {
  return new URLSearchParams(useLocation().search);
};

export default useQueryString;
