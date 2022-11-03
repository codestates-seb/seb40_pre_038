import { useState } from 'react';

function useInput(initialValue, initialError, name) {
  const [value, setValue] = useState(initialValue);
  const [error, setError] = useState(initialError);

  const reset = () => {
    setValue(initialValue);
  };

  const bind = {
    value,
    onChange: (e) => {
      setValue(e.target.value);
      if (e.target.value === '') {
        setError(`${name} is Missing`);
      } else {
        setError(initialError);
      }
    },
  };

  return [value, bind, reset, error];
}

export default useInput;
