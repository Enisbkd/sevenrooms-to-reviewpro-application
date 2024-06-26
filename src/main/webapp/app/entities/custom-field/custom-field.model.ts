import { IClient } from 'app/entities/client/client.model';

export interface ICustomField {
  id: string;
  systemName?: string | null;
  displayOrder?: number | null;
  name?: string | null;
  value?: string | null;
  client?: IClient | null;
}

export type NewCustomField = Omit<ICustomField, 'id'> & { id: null };
