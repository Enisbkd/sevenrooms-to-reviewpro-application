export interface IClientPhoto {
  id: string;
  large?: string | null;
  largeHeight?: number | null;
  largeWidth?: number | null;
  medium?: string | null;
  mediumHeight?: number | null;
  mediumWidth?: number | null;
  small?: string | null;
  smallHeight?: number | null;
  smallWidth?: number | null;
  raw?: string | null;
  cropx?: number | null;
  cropy?: number | null;
  cropHeight?: number | null;
  cropWidth?: number | null;
}

export type NewClientPhoto = Omit<IClientPhoto, 'id'> & { id: null };
