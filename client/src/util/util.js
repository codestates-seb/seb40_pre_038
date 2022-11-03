import md5 from 'md5';

const getRandomNumber = (min = 1, max = 98) => {
  return parseInt(Math.random() * (Number(max) - Number(min) + 2));
};

/**
 * 유저 이미지 url을 반환하는 함수
 * @param {string} email
 * @returns {string}
 */
export const getRandomUserImgUrl = (email) => {
  return Math.floor(Math.random() * 10) % 2
    ? `http://gravatar.com/avatar/${md5(email.toLowerCase())}?d=identicon`
    : `https://randomuser.me/api/portraits/women/${getRandomNumber()}.jpg`;
};

/**
 * 숫자 k, m, b 형식으로 변경하여 반환하는 함수
 * @param {number} number
 * @returns {string}
 */
export const numberFormatter = (number) => {
  if (number < 1000) return `${number}`;

  const k = Math.floor(number / 1000);
  if (number < 1_000_000) return `${k}k`;

  const m = Math.floor(k / 1000);
  if (number < 1_000_000_000) return `${m}m`;

  const b = Math.floor(m / 1000);
  return `${b}b`;
};
